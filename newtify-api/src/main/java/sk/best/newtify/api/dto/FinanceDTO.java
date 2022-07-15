package sk.best.newtify.api.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import sk.best.newtify.api.dto.ETopicType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * FinanceDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-07-14T12:35:24.888276700+02:00[Europe/Berlin]")
public class FinanceDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("title")
    private String title;

    @JsonProperty("text")
    private String text;

    public FinanceDTO uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Get uuid
     * @return uuid
     */

    @Schema(name = "uuid", required = false)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public FinanceDTO title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     * @return title
     */

    @Schema(name = "title", required = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public FinanceDTO text(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get text
     * @return text
     */

    @Schema(name = "text", required = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinanceDTO financeDTO = (FinanceDTO) o;
        return Objects.equals(this.uuid, financeDTO.uuid) &&
                Objects.equals(this.title, financeDTO.title) &&
                Objects.equals(this.text, financeDTO.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, text);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FinanceDTO {\n");
        sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    text: ").append(toIndentedString(text)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

