package org.inthergroup.ims.techMark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TechMarkRepository extends JpaRepository<TechMark, String> {


    public List<TechMark> getTechMarkByCandidateId(String id);
}
