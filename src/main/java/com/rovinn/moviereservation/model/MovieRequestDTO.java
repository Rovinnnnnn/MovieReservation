    package com.rovinn.moviereservation.model;

    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.Size;
    import lombok.Data;

    import java.util.List;

    @Data
    public class MovieRequestDTO {
        @NotBlank (message = "Title is required")
        private String title;
        @NotBlank (message = "Description is required")
        @Size(max = 400,message = "Description maximum is 400 character long")
        private String description;

        private Category genre;
        @NotBlank (message = "Poster is required")
        private String poster;
        @NotEmpty (message = "Show Times cannot be empty")
        private List<String> showTimes;
    }
