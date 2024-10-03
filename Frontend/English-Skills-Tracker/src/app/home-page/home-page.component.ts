import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { CardTemplateComponent } from '../card-template/card-template.component';
import { FooterComponent } from '../footer/footer.component';
@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [HeaderComponent,CardTemplateComponent,FooterComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.sass'
})
export class HomePageComponent{





}