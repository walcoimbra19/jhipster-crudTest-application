import { Moment } from 'moment';

export interface IVenda {
  id?: number;
  livro?: string;
  data?: Moment;
  total?: string;
  clienteId?: number;
}

export class Venda implements IVenda {
  constructor(public id?: number, public livro?: string, public data?: Moment, public total?: string, public clienteId?: number) {}
}
