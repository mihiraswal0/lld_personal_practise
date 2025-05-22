package com.lld.practise.systemDesign.urlShortner.entity;

import com.lld.practise.systemDesign.urlShortner.enums.AvailabilityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "available_ids")
public class AvailableId {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "base_64_id")
    private String base64Id;

    @Column(name = "status")
    private AvailabilityStatus availabilityStatus;

}
