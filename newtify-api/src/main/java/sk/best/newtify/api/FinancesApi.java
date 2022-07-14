package sk.best.newtify.api;

import sk.best.newtify.api.dto.FinanceDTO;
import sk.best.newtify.api.dto.CreateFinanceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import sk.best.newtify.api.dto.FinanceDTO;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-14T12:35:24.888276700+02:00[Europe/Berlin]")
@Validated
@Tag(name = "Finances", description = "Some finance information")
public interface FinancesApi {

    /**
     * POST /v1/finances
     * This is the endpoint which will create Article about finance
     *
     * @param createFinanceDTO Data model for article creation (required)
     * @return Hey, everything went well (status code 201)
     */
    @Operation(
            operationId = "createFinance",
            tags = { "finances" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Hey, everything went well", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/finances",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<FinanceDTO> createFinance(
            @Parameter(name = "CreateFinanceDTO", description = "Data model for article creation", required = true) @Valid @RequestBody CreateFinanceDTO createFinanceDTO
    );


    /**
     * DELETE /v1/articles/{articleUuid}
     * Endpoint which can be used to delete article resource specified by articleUuid
     *
     * @param articleUuid Article resource identifier (required)
     * @return Article was successfully deleted (status code 200)
     */
    @Operation(
            operationId = "deleteFinance",
            tags = { "finances" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Article was successfully deleted")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/v1/finances/{financeUuid}"
    )
    ResponseEntity<Void> deleteFinance(
            @Parameter(name = "financeUuid", description = "Article resource identifier", required = true) @PathVariable("financeUuid") String financeUuid
    );


    /**
     * GET /v1/articles/{articleUuid}
     * This is the endpoint which will return detail of article
     *
     * @param articleUuid  (required)
     * @return returns detail of article (status code 200)
     */
    @Operation(
            operationId = "retrieveFinance",
            tags = { "finances" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "returns detail of article", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/finances/{financeUuid}",
            produces = { "application/json" }
    )
    ResponseEntity<FinanceDTO> retrieveArticle(
            @Parameter(name = "financeUuid", description = "", required = true) @PathVariable("financeUuid") String financeUuid
    );


    /**
     * GET /v1/articles
     * This endpoint will return list of articles
     *
     * @param topic Used to filter articles by topic (optional)
     * @return It will return list of articles either by topic or everything (status code 200)
     */
    @Operation(
            operationId = "retrieveFinances",
            tags = { "finances" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "It will return list of articles either by topic or everything", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = FinanceDTO.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/finances",
            produces = { "application/json" }
    )
    ResponseEntity<List<FinanceDTO>> retrieveFinances(
            @Parameter(name = "topic", description = "Used to filter articles by topic") @Valid @RequestParam(value = "topic", required = false) String topic
    );


    /**
     * PUT /v1/articles/{articleUuid}
     * Endpoint to update already existing article specified by articleUuid
     *
     * @param articleUuid Article resource identifier (required)
     * @param createArticleDTO Data model with properties which are required for article update (required)
     * @return Article was successfully updated (status code 200)
     */
    @Operation(
            operationId = "updateFinance",
            tags = { "finances" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Article was successfully updated")
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/v1/finacnes/{financeUuid}",
            consumes = { "application/json" }
    )
    ResponseEntity<Void> updateFinance(
            @Parameter(name = "articleUuid", description = "Article resource identifier", required = true) @PathVariable("financeUuid") String financeUuid,
            @Parameter(name = "CreateArticleDTO", description = "Data model with properties which are required for article update", required = true) @Valid @RequestBody CreateFinanceDTO createFinanceDTO
    );
}