import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OwnerComponent} from './component/owner/owner.component';


const routes: Routes = [
  {path: '', redirectTo: 'owner', pathMatch: 'full'},
  {path: 'owner', component: OwnerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
