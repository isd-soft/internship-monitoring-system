package org.inthergroup.ims.internship.repository;

import org.inthergroup.ims.internship.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
}
