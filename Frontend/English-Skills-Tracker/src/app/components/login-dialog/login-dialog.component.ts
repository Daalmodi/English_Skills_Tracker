import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Users } from '../../models/users';
import { AuthService } from '../../services/auth/auth.service';
import { Login } from '../../models/login';


@Component({
  selector: 'app-login-dialog',
  standalone: true,
  imports: [MatDialogModule,MatFormFieldModule,MatInputModule,MatButtonModule,FormsModule],
  templateUrl: './login-dialog.component.html',
  styleUrl: './login-dialog.component.sass'
})
export class LoginDialogComponent {

  public user:Users; 
  public  dialogStatus = false;
  constructor( 
    private authService:AuthService,
    public dialogRef: MatDialogRef<LoginDialogComponent>
  
  ){
    this.user={
      id:NaN,
      name:"",
      email:"",
      lastLoginAt:"",
      createdAt:"",
      status:"",
      role :"",
      password:""
    }
  }

onSubmit(result:Users){
  if (!result.email || !result.password) {
    console.log("El resultado es indefinido");
    
    return;
  }
 const userlogged:Login = {
  email:result.email,
  password:result.password
 }
 this.authService.loginUser(userlogged).subscribe((response:any)=>{
  localStorage.setItem('token',JSON.stringify(response));// Guarda el token en el local storage 
 });
  this.dialogStatus = true; // Estado  verdadero del modal 
  this.dialogRef.close(this.dialogStatus);
 
 
}
}
