package cat.institutmarianao.sailing.ws.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.model.dto.TripTypeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

@Tag(name = "Trip Type", description = "Trip type API")

@RestController
@RequestMapping("/triptypes")
public class TripTypeController {

	/* Swagger */
	@Operation(summary = "Find all trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all")
	public List<TripTypeDto> findAllTripTypes() {

		// TODO find all trip types

		return null;
	}

	/* Swagger */
	@Operation(summary = "Find all trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all/{category}")
	public List<TripTypeDto> findAllTripTypesByCategory(@PathVariable("category") Category category) {

		// TODO find all trip types by category

		return null;
	}

	/* Swagger */
	@Operation(summary = "Find all group trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all/group")
	public List<TripTypeDto> findAllGroupTripTypes() {
		// TODO find all trip types with category GROUP
		return null;
	}

	/* Swagger */
	@Operation(summary = "Find all particular trip types")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TripType.class))) }, description = "Shipments retrieved ok")
	/**/
	@GetMapping("/find/all/private")
	public List<TripTypeDto> findAllPrivateTripTypes() {
		// TODO find all trip types with category PRIVATE
		return null;
	}

	/* Swagger */
	@Operation(summary = "Get user by id")
	@ApiResponse(responseCode = "200", description = "User retrieved ok")
	@ApiResponse(responseCode = "404", description = "Resource not found")
	/**/
	@GetMapping("/get/by/id/{id}")
	public TripTypeDto findById(@PathVariable("id") @NotNull Long id) {
		// TODO find trip type by id
		return null;
	}
}