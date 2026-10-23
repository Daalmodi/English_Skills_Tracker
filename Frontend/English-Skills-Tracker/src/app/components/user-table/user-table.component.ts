import { Component, OnInit, inject } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import { HeaderComponent } from '../../components/header/header.component';
import { MatTableModule } from '@angular/material/table';

import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule, MatDialog} from '@angular/material/dialog';


import { UsersService } from '../../services/users/users.service';
import { Users } from '../../models/users';



import { CreateUserDialogComponent } from '../../components/create-user-dialog/create-user-dialog.component';
import { UpdateUserDialogComponent } from '../../components/update-user-dialog/update-user-dialog.component';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-user-table',
  imports: [MatIconModule, MatTableModule, MatSortModule, MatPaginatorModule, MatButtonModule, MatDialogModule,DatePipe],
  templateUrl: './user-table.component.html',
  styleUrl: './user-table.component.sass'
})
export class UserTableComponent  implements OnInit{

  users:Users[]=[];

  columnsToDisplay=['id', 'name', 'email', 'createdAt', 'status', 'lastLoginAt','role','actions'];
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
