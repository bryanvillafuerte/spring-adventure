package com.project.springadventure.county;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/county")
@Tag(name = "County", description = "County management API")
public class CountyController {
  private final CountyService countyService;

  public CountyController(CountyService countyService) {
    this.countyService = countyService;
  }

  @GetMapping(value = "/{countyNumber}", produces = MediaType.TEXT_PLAIN_VALUE)
  @Operation(summary = "Get county name by county number", description = """
        Retrieves the name of a Norwegian county based on its county number.
        
        Curl example:
        ```bash
        curl http://localhost:8080/county/03
        ```
        """)
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "County name found"),
      @ApiResponse(responseCode = "500", description = "Error fetching county information")
  })
  public String getCountyName(
      @Parameter(description = "County number (e.g., 03 for Oslo)", example = "03")
      @PathVariable String countyNumber) {
    return countyService.getCounty(countyNumber);
  }
}