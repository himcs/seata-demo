package io.himcs.seata.storage.controller;

import io.himcs.seata.common.dto.PointsDTO;
import io.himcs.seata.storage.entiry.Points;
import io.himcs.seata.storage.repository.PointsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class PointsController {

    @Resource
    private PointsRepository pointsRepository;

    @PostMapping("/increase")
    Points increase(@RequestBody PointsDTO pointsDTO) {
        Points points = new Points();
        BeanUtils.copyProperties(pointsDTO, points);
        Points byUsername = pointsRepository.findByUsername(points.getUsername());
        byUsername.setPoints(byUsername.getPoints() + points.getPoints());
        Points save = pointsRepository.save(byUsername);
        return save;
    }
}
