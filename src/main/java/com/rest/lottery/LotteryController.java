package com.rest.lottery;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class LotteryController.
 */
@RestController
public class LotteryController {

	/** The all tickets. */
	private static Map<Long, Ticket> allTickets = new ConcurrentHashMap<Long, Ticket>();

	/** The id generator. */
	private final AtomicLong idGenerator = new AtomicLong();

	/**
	 * Creates the ticket.
	 *
	 * @param ticket the ticket
	 * @return the ticket
	 */
	/*
	 * Create a Ticket
	 */
	@PostMapping("/ticket")
	public Ticket createTicket(@RequestBody Ticket ticket) {
		Ticket ticketToBeSaved = new Ticket();
		ticketToBeSaved.setId(idGenerator.incrementAndGet());
		ticketToBeSaved.setLines(sortBefore(ticket.getLines()));
		allTickets.put(ticketToBeSaved.getId(), ticketToBeSaved);
		return ticketToBeSaved;
	}

	/**
	 * Gets the tickets.
	 *
	 * @return the tickets
	 */
	/*
	 * Return list of tickets
	 */
	@GetMapping("/ticket")
	public Collection<Ticket> getTickets() {
		return allTickets.values();
	}

	/**
	 * Gets the ticket by id.
	 *
	 * @param id the id
	 * @return the ticket by id
	 */
	/*
	 * Get individual ticket
	 */
	@GetMapping("/ticket/{id}")
	public Ticket getTicketById(@PathVariable long id) {
		return allTickets.get(id);
	}

	/**
	 * Amend ticket.
	 *
	 * @param id     the id
	 * @param ticket the ticket
	 * @return the ticket
	 */
	/*
	 * Amend ticket lines
	 */
	@PutMapping("/ticket/{id}")
	public Ticket amendTicket(@PathVariable long id, @RequestBody Ticket ticket) {
		Ticket ticketToBeammended = allTickets.get(id);
		if (ticketToBeammended != null && ticketToBeammended.isStatusChecked() == false) {
			List<Line> lines = ticketToBeammended.getLines();
			lines.addAll(ticket.getLines());
			ticketToBeammended.setLines(sortBefore(lines));
			allTickets.put(ticketToBeammended.getId(), ticketToBeammended);
		}
		return ticketToBeammended;
	}

	/**
	 * Gets the status by id.
	 *
	 * @param id the id
	 * @return the status by id
	 */
	/*
	 * Retrieve status of ticket
	 */
	@PutMapping("/status/{id}")
	public List<Integer> getStatusById(@PathVariable long id) {
		Ticket ticket = allTickets.get(id);
		ticket.setStatusChecked(true);
		allTickets.put(ticket.getId(), ticket);
		return ticket.getStatus();
	}

	/**
	 * Sort before.
	 *
	 * @param lines the lines
	 * @return the list
	 */
	private List<Line> sortBefore(List<Line> lines) {
		Collections.sort(lines, new Comparator<Line>() {
			public int compare(Line line1, Line line2) {
				return Integer.compare(line1.getResult(), line2.getResult());
			}
		});
		return lines;
	}

}
