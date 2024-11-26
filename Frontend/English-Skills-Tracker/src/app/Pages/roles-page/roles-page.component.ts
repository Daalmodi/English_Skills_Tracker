import { Component, inject, OnInit } from '@angular/core';
import { UsersService } from '../../services/users/users.service';
import { Users } from '../../models/users';

import {MatIconModule} from '@angular/material/icon';
import { HeaderComponent } from '../../components/header/header.component';
import { MatTableModule } from '@angular/material/table';

import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule, MatDialog} from '@angular/material/dialog';
import { CreateUserDialogComponent } from '../../components/create-user-dialog/create-user-dialog.component';


@Component({
  selector: 'app-roles-page',
  standalone: true,
  imports: [MatIconModule, HeaderComponent,MatTableModule,MatSortModule,MatPaginatorModule,MatButtonModule,MatDialogModule

  ],
  templateUrl: './roles-page.component.html',
  styleUrl: './roles-page.component.sass'
})
export class RolesPageComponent implements OnInit{
  users:Users[]=[];
  readonly dialog = inject(MatDialog);
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

  createUserDialog(){

    const dialogRef =this.dialog.open(CreateUserDialogComponent);
    dialogRef.afterClosed().subscribe(newUser => {
      this.usersService.createUser(newUser).subscribe();
    });
    
  }

}
