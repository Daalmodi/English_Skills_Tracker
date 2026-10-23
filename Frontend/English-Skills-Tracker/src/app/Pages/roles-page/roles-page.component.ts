import { Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { UserTableComponent } from '../../components/user-table/user-table.component';




@Component({
    selector: 'app-roles-page',
    imports: [HeaderComponent,UserTableComponent],
    templateUrl: './roles-page.component.html',
    styleUrl: './roles-page.component.sass'
})
export class RolesPageComponent{



}
