package cat.institutmarianao.sailing.ws.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.sailing.ws.SailingWsApplication;
import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.dto.ActionDto;
import cat.institutmarianao.sailing.ws.model.dto.BookedPlaceDto;
import cat.institutmarianao.sailing.ws.model.dto.TripDto;
import cat.institutmarianao.sailing.ws.service.ActionService;
import cat.institutmarianao.sailing.ws.service.BookedPlaceService;
import cat.institutmarianao.sailing.ws.service.TripService;
import cat.institutmarianao.sailing.ws.validation.groups.OnActionCreate;
import cat.institutmarianao.sailing.ws.validation.groups.OnTripCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Tag(name = "Trip", description = "Trip API")

@RestController
@RequestMapping("/trips")
@SecurityRequirement(name = "Bearer Authentication")
@Validated
public class TripController {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private TripService tripService;

	@Autowired
	private ActionService actionService;

	@Autowired
	private BookedPlaceService bookedPlaceService;

	@Operation(summary = "Retrieve all reserved trips (status is RESERVED)", description = "Retrieve all reserved trips from the database.")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripDto.class))) }, description = "Trips retrieved ok")
	@GetMapping(value = "/find/all")
	public @ResponseBody List<TripDto> findAll() {
		return tripService.findAll().stream().map(t -> conversionService.convert(t, TripDto.class))
				.collect(Collectors.toList());
	}

	@Operation(summary = "Retrieve all trips by username", description = "Retrieve all trips by username from the database.")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripDto.class))) }, description = "Trips retrieved ok")
	@GetMapping(value = "/find/all/by/client/username/{username}")
	public @ResponseBody List<TripDto> findAllByClientUsername(@PathVariable("username") String username) {
		return tripService.findAllByClientUsername(username).stream()
				.map(t -> conversionService.convert(t, TripDto.class)).collect(Collectors.toList());
	}

	@Operation(summary = "Save a trip", description = "Saves a trip in the database. The response is the stored trip from the database.")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TripDto.class)) }, description = "Trip saved ok")
	@ApiResponse(responseCode = "500", content = {
			@Content() }, description = "Error saving the trip. See response body for more details")
	@PostMapping(value = "/save")
	public TripDto save(@RequestBody @Validated(OnTripCreate.class) @NotNull TripDto tripDto) {
		return conversionService.convert(tripService.save(convertTrip(tripDto)), TripDto.class);
	}

	/* Swagger */
	@Operation(summary = "Save an action of a shipment (in its tracking)")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ActionDto.class)) }, description = "Action saved ok")
	/**/
	@PostMapping("/save/action")
	public ActionDto saveAction(@RequestBody @Validated(OnActionCreate.class) ActionDto actionDto) {

		return conversionService.convert(actionService.saveAction(convertAction(actionDto)), ActionDto.class);
	}

	@Operation(summary = "Get booked places", description = "Gets all booked places that a trip has")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TripDto.class)) }, description = "Booked places retrieved ok")
	@GetMapping(value = "/bookedPlaces/{trip_id}/{date}")
	public List<BookedPlaceDto> bookedPlaces(@PathVariable("trip_id") @NotNull Long id,
			@PathVariable("date") @NotNull @DateTimeFormat(pattern = SailingWsApplication.DATE_PATTERN) @Parameter(description = SailingWsApplication.DATE_PATTERN) Date date) {
		return bookedPlaceService.bookedPlaces(id, date).stream()
				.map(b -> conversionService.convert(b, BookedPlaceDto.class)).collect(Collectors.toList());
	}

	@Operation(summary = "Find tracking by trip id", description = "Gets the tracking of a trip by its id")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ActionDto.class)) }, description = "Tracking retrieved ok")

	@GetMapping("/find/tracking/by/id/{tripId}")
	public Iterable<ActionDto> findTrackingByTripId(@PathVariable("tripId") @Positive Long tripId) {
		List<Action> actions = (List<Action>) actionService.findTrackingByTripId(tripId);
		return actions.stream().map(a -> conversionService.convert(a, ActionDto.class)).collect(Collectors.toList());
	}

	private Trip convertTrip(TripDto tripDto) {
		return conversionService.convert(tripDto, Trip.class);
	}

	private Action convertAction(ActionDto actionDto) {
		return conversionService.convert(actionDto, Action.class);
	}

}