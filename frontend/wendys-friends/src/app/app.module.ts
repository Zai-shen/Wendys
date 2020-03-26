import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './component/header/header.component';
import {OwnerComponent} from './component/owner/owner.component';
import {HttpClientModule} from '@angular/common/http';
import { HorseComponent } from './component/horse/horse.component';
import { FormsModule } from '@angular/forms';
import { HorseDetailComponent } from './component/horse-detail/horse-detail.component';
import { PostHorseComponent } from './component/post-horse/post-horse.component';
import { GetAllHorseComponent } from './component/get-all-horse/get-all-horse.component';
import { SearchHorseComponent } from './component/search-horse/search-horse.component';
import { PostOwnerComponent } from './component/post-owner/post-owner.component';
import { SearchOwnerComponent } from './component/search-owner/search-owner.component';
import { GetAllOwnerComponent } from './component/get-all-owner/get-all-owner.component';
import { OwnerDetailComponent } from './component/owner-detail/owner-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    OwnerComponent,
    HorseComponent,
    HorseDetailComponent,
    PostHorseComponent,
    GetAllHorseComponent,
    SearchHorseComponent,
    PostOwnerComponent,
    SearchOwnerComponent,
    GetAllOwnerComponent,
    OwnerDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
