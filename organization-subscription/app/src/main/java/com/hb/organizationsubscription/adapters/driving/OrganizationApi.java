package com.hb.organizationsubscription.adapters.driving;


import static com.hb.organizationsubscription.adapters.driving.ApiExample.CREATED_ORGANIZATION;
import static com.hb.organizationsubscription.adapters.driving.ApiExample.ORGANIZATION_TO_CREATE;

import com.hb.error.management.model.ApiError;
import com.hb.organizationsubscription.adapters.driving.dto.OrganizationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface OrganizationApi {

  @Operation(
      operationId = "createOrganization",
      description = "Creates a new organization",
      responses = {
          @ApiResponse(responseCode = "200", description = "Successful response", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = OrganizationDto.class), examples = {
                  @ExampleObject(value = CREATED_ORGANIZATION)
              })
          }),
          @ApiResponse(responseCode = "400", description = "Bad request", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))
          })
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/organizations",
      produces = {"application/json"},
      consumes = {"application/json"}
  )
  ResponseEntity<OrganizationDto> createOrganization(
      @RequestBody(description = "Organization to create", required = true, content = {
          @Content(examples = {
              @ExampleObject(value = ORGANIZATION_TO_CREATE)})}) @Valid OrganizationDto organizationDto
  ) throws Exception;
}
