import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'autor',
        loadChildren: () => import('./autor/autor.module').then(m => m.JhipsterCrudTestApplicationAutorModule)
      },
      {
        path: 'livro',
        loadChildren: () => import('./livro/livro.module').then(m => m.JhipsterCrudTestApplicationLivroModule)
      },
      {
        path: 'cliente',
        loadChildren: () => import('./cliente/cliente.module').then(m => m.JhipsterCrudTestApplicationClienteModule)
      },
      {
        path: 'venda',
        loadChildren: () => import('./venda/venda.module').then(m => m.JhipsterCrudTestApplicationVendaModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterCrudTestApplicationEntityModule {}
