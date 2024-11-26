import { Component, inject} from '@angular/core';
import {} from '@angular/material/dialog';
import { MatDialogModule,MatDialogRef} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { Users } from '../../models/users';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-create-user-dialog',
  standalone: true,
  imports: [MatDialogModule,MatButtonModule,MatFormFieldModule,MatInputModule,MatSelectModule,FormsModule],
  templateUrl: './create-user-dialog.component.html',
  styleUrl: './create-user-dialog.component.sass'
})
export class CreateUserDialogComponent {

  constructor(public dialogRef: MatDialogRef<CreateUserDialogComponent>) { }
 user:Users = {
  id:NaN,
  name:"",
  email:"",
  lastLoginAt:"",
  createdAt:"",
  status:"",
  rol :""
 };

 
  onSubmit(){
  
    const newUser = {
      name: this.user.name,
      email: this.user.email,
      password: 'dsadsa', // Debes agregar un campo para el password
      status: this.user.status,
      role: this.user.rol,
    };

    this.dialogRef.close(newUser);
  }
  
}
