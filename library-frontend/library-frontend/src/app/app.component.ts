import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import {
  trigger,
  transition,
  style,
  animate,
  query,
  group
} from '@angular/animations';
import { slideInAnimation } from './route-animations';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet], 
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [slideInAnimation]
})

export class AppComponent {
  prepareRoute(outlet: RouterOutlet) {
    return outlet?.activatedRouteData?.['animation'];
  }
}
