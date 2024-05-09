package org.example.service;

import org.example.exception.ResourceNotFoundException;
import org.example.model.Park;
import org.example.model.ParkAccessibility;
import org.example.repository.ParkAccessibilityRepository;
import org.example.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class ParkAccessibilityService {
    private ParkRepository parkRepository;
    private ParkAccessibilityRepository parkAccessibilityRepository;


    // This enables us to use the methods from JpaRepository
    @Autowired
    public void setParkRepository(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Autowired
    public void setParkAccessibilityRepository(ParkAccessibilityRepository parkAccessibilityRepository) {
        this.parkAccessibilityRepository = parkAccessibilityRepository;
    }


    /**
     * Retrieves the accessibility details for a specific theme park by its ID and accessibility ID.
     *
     * @param parkId The ID of the theme park.
     * @param parkAccessibilityId The ID of the accessibility details to retrieve.
     * @return An Optional containing the theme park's accessibility details if found, else an empty Optional.
     * @throws ResourceNotFoundException if the accessibility details are not found or do not match the theme park ID.
     */
    public Optional<ParkAccessibility> getParkAccessibilityById(Long parkId, Long parkAccessibilityId) {
        Optional<Park> parkOptional = parkOptional = parkRepository.findById(parkId);
        Optional<ParkAccessibility> parkAccessibilityOptional = parkAccessibilityRepository.findById(parkAccessibilityId);

        if (parkAccessibilityOptional.isPresent() && parkOptional.get().getParkAccessibility().getId().equals(parkAccessibilityId)) {
            return parkAccessibilityOptional;
        } else {
            throw new ResourceNotFoundException("Error retrieving accessibility details for theme park with id " + parkId + " from the database. No accessibility details found.");
        }
    }

}
