package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.Exception.DuplicationResourceException;
import com.rovinn.moviereservation.Exception.ResourceNotFoundException;
import com.rovinn.moviereservation.Repository.MovieRepository;
import com.rovinn.moviereservation.model.MovieData;
import com.rovinn.moviereservation.model.MovieRequestDTO;
import com.rovinn.moviereservation.model.MovieResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplement implements MovieServiceInterface {
    private final MovieRepository movie;
    public MovieServiceImplement(MovieRepository movie) {
        this.movie = movie;
    }
        @Override
            public MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {
                if (movie.existsByTitle(movieRequestDTO.getTitle())) {
                     throw new DuplicationResourceException("Movie with title : "+ movieRequestDTO.getTitle() + "already exists");
                 }
                 MovieData addNew = new MovieData();
                 mapToEntity(movieRequestDTO, addNew);
                 MovieData saved = movie.save(addNew);

           return mapToResponseDTO(saved);
        }
        @Override
        public MovieResponseDTO getMovieById(Long id) {
             MovieData find = movie.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id '" + id + "' not found!"));
               return mapToResponseDTO(find);
        }
        @Override
        public List<MovieResponseDTO> getAllMovies(){
              return movie.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
        }
        @Override
        public MovieResponseDTO updateMovieById(Long id, MovieRequestDTO movieRequestDTO) {
              MovieData findMovie =  movie.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id '" + id + "' not found!"));
              MovieData updated = mapToEntity(movieRequestDTO, findMovie);
              movie.save(updated);
              return mapToResponseDTO(updated);
        }
        @Override
        public void deleteMovie(Long id){
           movie.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id '" + id + "' not found!"));
           movie.deleteById(id);
        }
        private MovieResponseDTO mapToResponseDTO(MovieData data) {
            MovieResponseDTO responseDTO = new MovieResponseDTO();
            responseDTO.setId(data.getId());
            responseDTO.setTitle(data.getTitle());
            responseDTO.setDescription(data.getDescription());
            responseDTO.setGenre(data.getGenre());
            responseDTO.setPoster(data.getPoster());
            return responseDTO;
        }
        private MovieData mapToEntity(MovieRequestDTO movieRequestDTO, MovieData entity) {
            entity.setTitle(movieRequestDTO.getTitle());
            entity.setDescription(movieRequestDTO.getDescription());
            entity.setGenre(movieRequestDTO.getGenre());
            entity.setPoster(movieRequestDTO.getPoster());
            return entity;
        }
}
