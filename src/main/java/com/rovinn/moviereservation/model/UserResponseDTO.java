    package com.rovinn.moviereservation.model;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserResponseDTO {
        private Long id;
        private String email;
        private String token;
     public UserResponseDTO(Long id, String email) {
         this.id = id;
          this.email = email;
     }
    }
