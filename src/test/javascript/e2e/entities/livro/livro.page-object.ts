import { element, by, ElementFinder } from 'protractor';

export class LivroComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-livro div table .btn-danger'));
  title = element.all(by.css('jhi-livro div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class LivroUpdatePage {
  pageTitle = element(by.id('jhi-livro-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  tituloInput = element(by.id('field_titulo'));
  descricaoInput = element(by.id('field_descricao'));
  precoInput = element(by.id('field_preco'));

  autorSelect = element(by.id('field_autor'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTituloInput(titulo: string): Promise<void> {
    await this.tituloInput.sendKeys(titulo);
  }

  async getTituloInput(): Promise<string> {
    return await this.tituloInput.getAttribute('value');
  }

  async setDescricaoInput(descricao: string): Promise<void> {
    await this.descricaoInput.sendKeys(descricao);
  }

  async getDescricaoInput(): Promise<string> {
    return await this.descricaoInput.getAttribute('value');
  }

  async setPrecoInput(preco: string): Promise<void> {
    await this.precoInput.sendKeys(preco);
  }

  async getPrecoInput(): Promise<string> {
    return await this.precoInput.getAttribute('value');
  }

  async autorSelectLastOption(): Promise<void> {
    await this.autorSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async autorSelectOption(option: string): Promise<void> {
    await this.autorSelect.sendKeys(option);
  }

  getAutorSelect(): ElementFinder {
    return this.autorSelect;
  }

  async getAutorSelectedOption(): Promise<string> {
    return await this.autorSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class LivroDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-livro-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-livro'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
