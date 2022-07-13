package com.spring.pfe.controllers;


import com.spring.pfe.models.*;
import com.spring.pfe.repository.DemandeRepository;
import com.spring.pfe.repository.EtapeRepository;
import com.spring.pfe.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private EtapeRepository iTape;
    @Autowired
    QuizRepository iquiz;


    @RequestMapping(value="/add/{etape_id_etape}",method= RequestMethod.POST)
    public Response<Quiz> addQuiz(@RequestBody Quiz t ,@PathVariable("etape_id_etape") Long etape_id_etape ) {
        try {
            Etape u = iTape.findById(etape_id_etape).orElse(null);
            t.setEtape(u);
            return new Response<Quiz>("200", "Creat quiz", iquiz.save(t));

        } catch (Exception e) {
            return new Response<Quiz>("406", e.getMessage(), null);
        }

    }


    @RequestMapping(value="/addd",method= RequestMethod.POST)
    public Response<Quiz> addQuizz(@RequestBody Quiz t ) {
        try {

            return new Response<Quiz>("200", "Creat quiz", iquiz.save(t));

        } catch (Exception e) {
            return new Response<Quiz>("406", e.getMessage(), null);
        }

    }


    @GetMapping("/getQuiz/{id}")
    public Response<Quiz> GetQuizById(@PathVariable("id") Long id) {
        try {
            return new Response<Quiz>("200", "Get quiz By ID", iquiz.findById(id).orElse(null));

        } catch (Exception e) {
            return new Response<Quiz>("406", e.getMessage(), null);
        }

    }

    @GetMapping("/")
    public Response<List<Quiz>> findAllQuiz (){
        // return /*iCategory.findAll();*/

        try {

            return new Response<List<Quiz>>("200", "Get all quiz", iquiz.findAll());
        }catch (Exception e){
            return new Response<List<Quiz>>("406", e.getMessage(), null);

        }
    }



    @DeleteMapping("/deleteQuiz/{id}")
    public Response<Quiz> deleteQuiz (@PathVariable("id") Long  id){

        iquiz.deleteById(id);
        try{
            return new Response<Quiz>("200", "Quiz deleted", null);
        } catch (Exception e){
            return new Response<Quiz> ("406",e.getMessage(),null);
        }
    }


    @PutMapping("/updateQuiz/{id}")
    public Response<Quiz> updateQuiz(@PathVariable("id") Long id,@RequestBody Quiz d) {
        try {

            Quiz oldCours = iquiz.findById(id).orElse(null);
            d.setQuestions(d.getQuestions() == null ? oldCours.getQuestions() : d.getQuestions());
            d.setDescription(d.getDescription() == null ? oldCours.getDescription() : d.getDescription());
            d.setTitle(d.getTitle() == null ? oldCours.getTitle() : d.getTitle());
            d.setEtape(d.getEtape() == null ? oldCours.getEtape() : d.getEtape());
            d.setMaxMarks(d.getMaxMarks() == null ? oldCours.getMaxMarks() : d.getMaxMarks());
            d.setNumberOfQuestions(d.getNumberOfQuestions() == null ? oldCours.getNumberOfQuestions() : d.getNumberOfQuestions());

            d.setActive(d.isActive() );

            d.setId(id);


            return new Response<Quiz>("200","Formation updated", iquiz.save(d));
        }catch (Exception e){
            return new Response<Quiz>("406", e.getMessage(), null);

        }
    }


}
