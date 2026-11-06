import { Component, DestroyRef, OnInit, inject } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';

import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';

import { UsersService } from '../../services/users/users.service';
import { Users } from '../../models/users';

import { CreateUserDialogComponent } from '../../components/create-user-dialog/create-user-dialog.component';
import { UpdateUserDialogComponent } from '../../components/update-user-dialog/update-user-dialog.component';
import { DatePipe, SlicePipe } from '@angular/common';

@Component({
  selector: 'app-user-table',
  imports: [MatIconModule, MatTableModule, MatSortModule, MatPaginatorModule, MatButtonModule, MatDialogModule, DatePipe, SlicePipe],
  templateUrl: './user-table.component.html',
  styleUrl: './user-table.component.sass'
})
export class UserTableComponent implements OnInit {

  users: Users[] = [];

  columnsToDisplay = ['name', 'lastName', 'email', 'role', 'createdAt', 'status', 'actions'];
  readonly dialog = inject(MatDialog);
  private readonly destroyRef = inject(DestroyRef);

  constructor(private usersService: UsersService) {}

  ngOnInit(): void {
    this.usersService.getUsersObservable()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response: Users[]) => {
        this.users = response;
      });
    this.usersService.getUsers()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe();
  }

  editUser(user: Users): void {
    const dialogRef = this.dialog.open(UpdateUserDialogComponent, {
      data: user
    });
    dialogRef.afterClosed()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((result) => {
        if (result?.dialogStatus) {
          this.usersService.editUserId(result.updatedUser)
            .pipe(takeUntilDestroyed(this.destroyRef))
            .subscribe();
        }
      });
  }

  deleteUser(user: Users): void {
    this.usersService.deleteUser(user.id)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe();
  }

  createUserDialog(): void {
    const dialogRef = this.dialog.open(CreateUserDialogComponent);
    dialogRef.afterClosed()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((result) => {
        if (result?.dialogStatus) {
          this.usersService.createUser(result.newUser)
            .pipe(takeUntilDestroyed(this.destroyRef))
            .subscribe();
        }
      });
  }

}
