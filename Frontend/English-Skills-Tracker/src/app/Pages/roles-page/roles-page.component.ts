import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';



@Component({
  selector: 'app-roles-page',
  standalone: true,
  imports: [],
  templateUrl: './roles-page.component.html',
  styleUrl: './roles-page.component.sass'
})
export class RolesPageComponent implements OnInit{
  
  constructor(private usersService: UsersService){}

  ngOnInit():void{
    this.usersService.getUsers().subscribe((response:any)=>{
      console.log(response);
      
    });
  }

}
