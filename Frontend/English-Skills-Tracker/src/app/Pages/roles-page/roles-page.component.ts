import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users/users.service';
import { Users } from '../../models/users';

import {MatIconModule} from '@angular/material/icon';
import { HeaderComponent } from '../../components/header/header.component';
import { MatTableModule } from '@angular/material/table';

import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatButtonModule} from '@angular/material/button';



@Component({
  selector: 'app-roles-page',
  standalone: true,
  imports: [MatIconModule, HeaderComponent,MatTableModule,MatSortModule,MatPaginatorModule,MatButtonModule],
  templateUrl: './roles-page.component.html',
  styleUrl: './roles-page.component.sass'
})
export class RolesPageComponent implements OnInit{
  users:Users[]=[];
  checked = false;
  disabled = false;
  constructor(private usersService: UsersService){}

  ngOnInit():void{
    this.usersService.getUsers().subscribe((response:any)=>{
      this.users= response;
    });
  }

  editUser(user:any){
    console.log(user);
    
  }
  deleteUser(user:any){
    this.usersService.deleteUser(user.id).subscribe();
    
  }

  createUser(){
    this.usersService.createUser().subscribe();
    
  }

}
