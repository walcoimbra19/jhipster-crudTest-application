import { ILivro } from 'app/shared/model/livro.model';

export interface IAutor {
  id?: number;
  nome?: string;
  email?: string;
  livros?: ILivro[];
}

export class Autor implements IAutor {
  constructor(public id?: number, public nome?: string, public email?: string, public livros?: ILivro[]) {}
}
