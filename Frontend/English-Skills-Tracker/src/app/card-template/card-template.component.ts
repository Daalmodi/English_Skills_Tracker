import { Component, OnInit } from '@angular/core';
import { Skills } from '../models/skills';

@Component({
  selector: 'app-card-template',
  standalone: true,
  imports: [],
  templateUrl: './card-template.component.html',
  styleUrl: './card-template.component.sass'
})
export class CardTemplateComponent implements OnInit {

skills: Skills ={
  title:["Listening","Speaking","Writing","Reading"],
  id:[0,1,2,3],
  description:["Listening is a crucial skill for mastering a language. Strengthening listening abilities improves comprehension, pronunciation, and the capacity to follow conversations or directions precisely. In today's interconnected world, where verbal communication plays a key role, strong listening skills are vital for successful communication and career advancement. The platform supports users in enhancing their listening proficiency by providing tailored audio materials that adjust to their skill level.","Speaking is a key skill for achieving language fluency. Developing speaking abilities improves pronunciation, clarity, and confidence in expressing ideas. In a world where verbal communication is essential for building relationships and professional success, strong speaking skills are critical. The platform helps users enhance their speaking proficiency.","Writing is an essential skill for language mastery. Strengthening writing abilities enhances grammar, clarity, and the ability to organize thoughts effectively. In today's world, where written communication is crucial in both academic and professional settings, strong writing skills are indispensable. The platform supports users in improving their writing proficiency through personalized tasks and feedback that grow with their development.","Reading is a fundamental skill for language mastery. Improving reading skills boosts vocabulary, comprehension, and critical thinking. In today's world, where information is often shared through text, strong reading skills are essential for academic success and career opportunities. The platform helps users develop reading proficiency by offering personalized tracking progress."],

  image: ['https://live.staticflickr.com/65535/54020402641_d3b269625e_o.jpg','https://live.staticflickr.com/65535/54020402651_48f008a517_o.jpg','https://live.staticflickr.com/65535/54020402656_46ecf6ec58_o.jpg','https://live.staticflickr.com/65535/54019505967_654ca69ab5_o.jpg']
};



ngOnInit(): void {
  

}
}
