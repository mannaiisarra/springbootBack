package com.spring.pfe.controllers;


import com.spring.pfe.models.*;
import com.spring.pfe.repository.CoursRepository;
import com.spring.pfe.repository.EtapeRepository;
import com.spring.pfe.repository.VideoRepository;
import com.spring.pfe.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/video")
@CrossOrigin("*")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    private StorageService storageService;

    @Autowired
    EtapeRepository itape;

    @RequestMapping(value="/add/{etape_id_etape}", method= RequestMethod.POST)
    public Response<Video> addVideo(Video p, @RequestParam("file") MultipartFile file, @PathVariable("etape_id_etape") Long etape_id_etape) {
        try {
            if(p!=null) {
             /*   Date d=new Date();
                String date=""+d.getDay()+d.getMonth()+d.getYear()+d.getHours()+d.getMinutes()+d.getSeconds();*/
                String fileName =storageService.createNameImage(file);
                storageService.store(file,fileName);
                p.setPhoto(fileName);
                p.setName(p.getName());
                Etape e = itape.findById(etape_id_etape).orElse(null);
                p.setEtape(e);
                return new Response<Video>("200", "Creat Video", videoRepository.save(p));
            } else {
                return new Response<Video>("500", "Video not found", null);
            }
        } catch (Exception e) {
            return new Response<Video>("406", e.getMessage(), null);
        }

    }



    @RequestMapping(value="/adddf",method= RequestMethod.POST)
    public Response<Video> addQuizz( @RequestParam("file") MultipartFile file) {
        try {

            Video p = new Video();
            String fileName =storageService.createNameImage(file);
            storageService.store(file,fileName);
            p.setPhoto(fileName);
            p.setName(p.getName());
            return new Response<Video>("200", "Creat quiz", videoRepository.save(p));

        } catch (Exception e) {
            return new Response<Video>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/findVideo/{etape_id_etape}")
    public Response<List<Video>> findCoursByEtape(@PathVariable Long etape_id_etape){
        try {

            return new Response<List<Video>>("200", "Get all video by Etape", videoRepository.getAllVideoByEtape(etape_id_etape));

        }catch (Exception e){
            return new Response<List<Video>>("406", e.getMessage(), null);

        }
    }



    @GetMapping("/findById/{id}")
    public Response<Video> findById(@PathVariable Long id){
        try {

            return new Response<Video>("200", "Get video by id", videoRepository.findById(id).orElse(null));
        }catch (Exception e){
            return new Response<Video>("406", e.getMessage(), null);

        }
    }
}
