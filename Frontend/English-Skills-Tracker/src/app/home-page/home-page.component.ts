import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { CardTemplateComponent } from '../card-template/card-template.component';
@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [HeaderComponent,CardTemplateComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.sass'
})
export class HomePageComponent{





}