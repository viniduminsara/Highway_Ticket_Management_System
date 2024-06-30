package lk.ijse.ticket_service.service.impl;

import lk.ijse.ticket_service.dto.TicketDTO;
import lk.ijse.ticket_service.entity.TicketEntity;
import lk.ijse.ticket_service.exception.NotFoundException;
import lk.ijse.ticket_service.repository.TicketRepository;
import lk.ijse.ticket_service.service.TicketService;
import lk.ijse.ticket_service.util.Conversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepo;
    private final Conversion conversion;

    @Override
    public void createTicket(TicketDTO ticketDTO) {
        TicketEntity ticketEntity = conversion.toTicketEntity(ticketDTO);
        ticketEntity.setIssuedAt(Timestamp.valueOf(LocalDateTime.now()));
        ticketRepo.save(ticketEntity);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return conversion.toTicketDTOList(ticketRepo.findAll());
    }

    @Override
    public TicketDTO getSelectedTicket(String id) {
        if (!ticketRepo.existsById(id)) throw new NotFoundException("Ticket not found");
        return conversion.toTicketDTO(ticketRepo.getReferenceById(id));
    }

    @Override
    public void updateTicketStatus(String id, String collectedLocation) {
        Optional<TicketEntity> ticketEntity = ticketRepo.findById(id);
        if (ticketEntity.isEmpty()) throw new NotFoundException("Ticket not found");
        ticketEntity.get().setCollectedLocation(collectedLocation);
        ticketEntity.get().setCollectedAt(Timestamp.valueOf(LocalDateTime.now()));
    }
}
