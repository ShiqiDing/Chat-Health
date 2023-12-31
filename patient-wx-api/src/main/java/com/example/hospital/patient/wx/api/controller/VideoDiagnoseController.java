package com.example.hospital.patient.wx.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.example.hospital.patient.wx.api.common.R;
import com.example.hospital.patient.wx.api.controller.form.CreateVideoDiagnoseForm;
import com.example.hospital.patient.wx.api.controller.form.SearchOnlineDoctorListForm;
import com.example.hospital.patient.wx.api.db.pojo.VideoDiagnoseEntity;
import com.example.hospital.patient.wx.api.service.VideoDiagnoseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Shiqi
 */
@RestController
@RequestMapping("/video_diagnose")
public class VideoDiagnoseController {
    @Resource
    private VideoDiagnoseService videoDiagnoseService;
    @PostMapping("searchOnlineDoctorList")
    @SaCheckLogin
    public R searchOnlineDoctorList(@RequestBody @Valid SearchOnlineDoctorListForm form) {
        ArrayList<HashMap> list = videoDiagnoseService.searchOnlineDoctorList(form.getSubName(), form.getJob());
        return R.ok().put("result", list);
    }

    @PostMapping("createVideoDiagnose")
    @SaCheckLogin
    public R createVideoDiagnose(@RequestBody @Valid CreateVideoDiagnoseForm form) {
        int userId = StpUtil.getLoginIdAsInt();
        VideoDiagnoseEntity entity = BeanUtil.toBean(form, VideoDiagnoseEntity.class);
        HashMap result = videoDiagnoseService.createVideoDiagnose(userId, entity);
        return R.ok().put("result", result);
    }
}

