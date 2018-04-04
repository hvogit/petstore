import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [`
    div#top-container {
      padding-top: 70px;
    }
  `]
})
export class AppComponent {
  title = 'Pet Store Demo';
}
