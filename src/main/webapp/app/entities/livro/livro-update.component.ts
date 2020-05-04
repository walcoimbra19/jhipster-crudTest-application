import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILivro, Livro } from 'app/shared/model/livro.model';
import { LivroService } from './livro.service';
import { IAutor } from 'app/shared/model/autor.model';
import { AutorService } from 'app/entities/autor/autor.service';

@Component({
  selector: 'jhi-livro-update',
  templateUrl: './livro-update.component.html'
})
export class LivroUpdateComponent implements OnInit {
  isSaving = false;
  autors: IAutor[] = [];

  editForm = this.fb.group({
    id: [],
    titulo: [],
    descricao: [],
    preco: [],
    autorId: []
  });

  constructor(
    protected livroService: LivroService,
    protected autorService: AutorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ livro }) => {
      this.updateForm(livro);

      this.autorService.query().subscribe((res: HttpResponse<IAutor[]>) => (this.autors = res.body || []));
    });
  }

  updateForm(livro: ILivro): void {
    this.editForm.patchValue({
      id: livro.id,
      titulo: livro.titulo,
      descricao: livro.descricao,
      preco: livro.preco,
      autorId: livro.autorId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const livro = this.createFromForm();
    if (livro.id !== undefined) {
      this.subscribeToSaveResponse(this.livroService.update(livro));
    } else {
      this.subscribeToSaveResponse(this.livroService.create(livro));
    }
  }

  private createFromForm(): ILivro {
    return {
      ...new Livro(),
      id: this.editForm.get(['id'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
      preco: this.editForm.get(['preco'])!.value,
      autorId: this.editForm.get(['autorId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILivro>>): void {
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

  trackById(index: number, item: IAutor): any {
    return item.id;
  }
}
