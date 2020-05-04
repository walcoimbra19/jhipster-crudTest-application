import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVenda, Venda } from 'app/shared/model/venda.model';
import { VendaService } from './venda.service';
import { ICliente } from 'app/shared/model/cliente.model';
import { ClienteService } from 'app/entities/cliente/cliente.service';

@Component({
  selector: 'jhi-venda-update',
  templateUrl: './venda-update.component.html'
})
export class VendaUpdateComponent implements OnInit {
  isSaving = false;
  clientes: ICliente[] = [];
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    livro: [],
    data: [],
    total: [],
    clienteId: []
  });

  constructor(
    protected vendaService: VendaService,
    protected clienteService: ClienteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ venda }) => {
      this.updateForm(venda);

      this.clienteService.query().subscribe((res: HttpResponse<ICliente[]>) => (this.clientes = res.body || []));
    });
  }

  updateForm(venda: IVenda): void {
    this.editForm.patchValue({
      id: venda.id,
      livro: venda.livro,
      data: venda.data,
      total: venda.total,
      clienteId: venda.clienteId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const venda = this.createFromForm();
    if (venda.id !== undefined) {
      this.subscribeToSaveResponse(this.vendaService.update(venda));
    } else {
      this.subscribeToSaveResponse(this.vendaService.create(venda));
    }
  }

  private createFromForm(): IVenda {
    return {
      ...new Venda(),
      id: this.editForm.get(['id'])!.value,
      livro: this.editForm.get(['livro'])!.value,
      data: this.editForm.get(['data'])!.value,
      total: this.editForm.get(['total'])!.value,
      clienteId: this.editForm.get(['clienteId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVenda>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICliente): any {
    return item.id;
  }
}
