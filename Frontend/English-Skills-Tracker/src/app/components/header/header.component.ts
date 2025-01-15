import { Component, inject } from '@angular/core';
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
export class HeaderComponent {
  readonly dialog = inject(MatDialog);

loginForm(){

  const dialogRef = this.dialog.open(LoginDialogComponent);

  dialogRef.afterClosed().subscribe(()=>{
    
  });
}
}
