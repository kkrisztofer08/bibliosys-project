import { animate, style, transition, trigger } from '@angular/animations';



export const slideInAnimation =
  trigger('routeAnimations', [
    transition('* <=> *', [
      style({ opacity: 0, transform: 'translateX(100%)' }),
      animate('1400ms ease', style({ opacity: 1, transform: 'translateX(0)' }))
    ])
  ]);
