import { Routes } from '@angular/router';
import { HomePageComponent } from './Pages/home-page/home-page.component';
import { RolesPageComponent } from './Pages/roles-page/roles-page.component';

export const routes: Routes = [
    {path: '', component: HomePageComponent},
    {path: 'roles-page', component: RolesPageComponent},
    

];
