import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-default',
  standalone: true,
  imports: [],
  templateUrl: './default.component.html',
  styleUrl: './default.component.css'
})
export class DefaultComponent {

  constructor(private router: Router){

  }
  navegation(path: string) {
    this.router.navigate([path]);
  }
}
