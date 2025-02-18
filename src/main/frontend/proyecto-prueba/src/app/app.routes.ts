import { Routes } from '@angular/router';
import { DefaultComponent } from './layouts/default/default.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { RegistroComponent } from './pages/empleado/components/registro/registro.component';
import { ListadoComponent } from './pages/empleado/components/listado/listado.component';

export const routes: Routes = [

  {
    path: '',
    title: 'Control de empleados | Home',
    component: DashboardComponent,
    children:[
      {
        path: 'empleado-registro', component: RegistroComponent
      },
      {
        path: 'empleado-listado', component: ListadoComponent
      }
    ]
  },
  {
    path: '404',
    title: 'pagina not found',
    component: DefaultComponent
  },
  { path: '**', redirectTo: '404' },
];
