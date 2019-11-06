package com.task.pizza.service;

import com.task.pizza.domain.Extra;
import com.task.pizza.repo.ExtraRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ExtraServiceImpl implements ExtraService {

    private ExtraRepo extraRepo;

    @Autowired
    public ExtraServiceImpl(ExtraRepo extraRepo) {
        this.extraRepo = extraRepo;
    }

    @Override
    public List<Extra> extraList() {

        ArrayList<Extra> extras = new ArrayList<>();

        Iterable<Extra> extraIterable = extraRepo.findAll();
        extraIterable.forEach(extras::add);
        return extras;
    }
}
