package lk.ijse.ticket_service.controller;

import jakarta.validation.Valid;
import lk.ijse.ticket_service.dto.TicketDTO;
import lk.ijse.ticket_service.exception.NotFoundException;
import lk.ijse.ticket_service.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping(value = "/health")
    public String health(){
        return "Ticket ok";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketDTO ticketDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            ticketService.createTicket(ticketDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllVehicles(){
        try {
            return ResponseEntity.ok(ticketService.getAllTickets());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSelectedTicket(@PathVariable("id") String id){
        try {
            return ResponseEntity.ok(ticketService.getSelectedTicket(id));
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTicketStatus(@PathVariable("id") String id, @RequestParam String collectedLocation){
        try {
            ticketService.updateTicketStatus(id, collectedLocation);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
