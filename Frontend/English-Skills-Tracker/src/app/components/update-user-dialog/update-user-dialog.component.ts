import { Component,Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Users } from '../../models/users';
@Component({
  selector: 'app-update-user-dialog',
  standalone: true,
  imports: [MatDialogModule,MatFormFieldModule,MatInputModule,MatSelectModule,MatButtonModule,FormsModule],
  templateUrl: './update-user-dialog.component.html',
  styleUrl: './update-user-dialog.component.sass'
})
export class UpdateUserDialogComponent {
  public  dialogStatus = false;
  public user: Users;
  constructor(
    public dialogRef: MatDialogRef<UpdateUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Users
  ) { this.user=data;}

  onSubmit(){
    const updatedUser = {
      id:this.user.id,
      name: this.user.name,
      email: this.user.email,
      status: this.user.status,
      role: this.user.role,
    };
    this.dialogStatus = true;
    this.dialogRef.close({updatedUser,dialogStatus:this.dialogStatus});
  }

}
