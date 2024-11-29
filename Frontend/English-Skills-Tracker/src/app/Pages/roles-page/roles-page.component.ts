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
import { UpdateUserDialogComponent } from '../../components/update-user-dialog/update-user-dialog.component';


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
    this.usersService.getUsersObservable().subscribe((response:Users[])=>{
      this.users= response;
    });
    this.usersService.getUsers().subscribe();
  }

  editUser(user:Users){
    const dialogRef = this.dialog.open(UpdateUserDialogComponent,{
      data:user
    });
    dialogRef.afterClosed().subscribe((result)=>{
      if(result.dialogStatus){
        this.usersService.editUserId(result.updatedUser).subscribe();
      }
    })
  }


  deleteUser(user:Users){

  this.usersService.deleteUser(user.id).subscribe();
    
  }

  createUserDialog(){

    const dialogRef =this.dialog.open(CreateUserDialogComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if(result.dialogStatus){
        this.usersService.createUser(result.newUser).subscribe();
      }
    });
    
  }

}
