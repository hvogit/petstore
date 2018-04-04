import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {LoginComponent} from './core/components/login/login.component';
import {NotFoundComponent} from './core/components/not-found/not-found.component';
import {AppRoutingModule} from './app-routing.module';
import {PetsModule} from './pets/pets.module';
import {AuthService} from './core/services/auth.service';
import {JwtModule} from '@auth0/angular-jwt';
import {HttpClientModule} from '@angular/common/http';
import {environment} from '../environments/environment';
import {AuthGuardService} from './core/services/auth-guard.service';
import {RoleGuardService} from './core/services/role-guard.service';

export function tokenGetter() {
  return localStorage.getItem(environment.ACCESS_TOKEN);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: [environment.API_DOMAIN]
      }
    }),
    PetsModule,
    AppRoutingModule
  ],
  providers: [AuthService, AuthGuardService, RoleGuardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
