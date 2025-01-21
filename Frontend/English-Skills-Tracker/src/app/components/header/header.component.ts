import { Component, inject, OnInit } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { MatDialogModule,MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatButtonModule,MatIconModule,MatDialogModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.sass'
})
export class HeaderComponent implements OnInit {
session: boolean = false;
ngOnInit(): void {
  this.loginState();

}

loginState(){
  if(typeof localStorage !== 'undefined'){
    
    const token = localStorage.getItem('token');
    if(token){
      this.session = true;
    }
  }else{
    this.session = false; 
  }
}




  readonly dialog = inject(MatDialog);

loginForm(){

  const dialogRef = this.dialog.open(LoginDialogComponent);

  dialogRef.afterClosed().subscribe(()=>{
    this.loginState();
    this.session = true;
  });

}
logout(){
  localStorage.removeItem('token');
  this.session = false;
  this.loginState();
}
}
