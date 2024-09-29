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
  description:["lore1","lom2","lorem3","lorem4"],

  image: ['https://live.staticflickr.com/65535/54020402641_d3b269625e_o.jpg','https://live.staticflickr.com/65535/54020402651_48f008a517_o.jpg','https://live.staticflickr.com/65535/54020402656_46ecf6ec58_o.jpg','https://live.staticflickr.com/65535/54019505967_654ca69ab5_o.jpg']
};



ngOnInit(): void {
  

}
}
