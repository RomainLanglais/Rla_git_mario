@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Ajouter un nouveau film</h5>
                    <a href="{{ route('films.index') }}" class="btn btn-secondary btn-sm">
                        <i class="bi bi-arrow-left"></i> Retour à la liste
                    </a>
                </div>

                <div class="card-body">
                    @if(session('error'))
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle"></i> {{ session('error') }}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    @endif

                    <form action="{{ route('films.store') }}" method="POST">
                        @csrf

                        <div class="row">
                            <div class="col-md-8">
                                <div class="mb-3">
                                    <label for="title" class="form-label">Titre <span class="text-danger">*</span></label>
                                    <input type="text"
                                           class="form-control @error('title') is-invalid @enderror"
                                           id="title"
                                           name="title"
                                           value="{{ old('title') }}"
                                           required>
                                    @error('title')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="releaseYear" class="form-label">Année de sortie <span class="text-danger">*</span></label>
                                    <input type="number"
                                           class="form-control @error('releaseYear') is-invalid @enderror"
                                           id="releaseYear"
                                           name="releaseYear"
                                           value="{{ old('releaseYear', date('Y')) }}"
                                           min="1888"
                                           max="{{ date('Y') + 5 }}"
                                           required>
                                    @error('releaseYear')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control @error('description') is-invalid @enderror"
                                      id="description"
                                      name="description"
                                      rows="4">{{ old('description') }}</textarea>
                            @error('description')
                                <div class="invalid-feedback">{{ $message }}</div>
                            @enderror
                        </div>

                        <div class="row">
                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="length" class="form-label">Durée (minutes) <span class="text-danger">*</span></label>
                                    <input type="number"
                                           class="form-control @error('length') is-invalid @enderror"
                                           id="length"
                                           name="length"
                                           value="{{ old('length') }}"
                                           min="1"
                                           required>
                                    @error('length')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="rating" class="form-label">Classification <span class="text-danger">*</span></label>
                                    <select class="form-select @error('rating') is-invalid @enderror"
                                            id="rating"
                                            name="rating"
                                            required>
                                        <option value="">Sélectionnez...</option>
                                        <option value="G" {{ old('rating') == 'G' ? 'selected' : '' }}>G - Tous publics</option>
                                        <option value="PG" {{ old('rating') == 'PG' ? 'selected' : '' }}>PG - Accord parental souhaitable</option>
                                        <option value="PG-13" {{ old('rating') == 'PG-13' ? 'selected' : '' }}>PG-13 - Accord parental fortement conseillé</option>
                                        <option value="R" {{ old('rating') == 'R' ? 'selected' : '' }}>R - Interdit aux mineurs</option>
                                        <option value="NC-17" {{ old('rating') == 'NC-17' ? 'selected' : '' }}>NC-17 - Strictement adulte</option>
                                    </select>
                                    @error('rating')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="languageId" class="form-label">Langue <span class="text-danger">*</span></label>
                                    <select class="form-select @error('languageId') is-invalid @enderror"
                                            id="languageId"
                                            name="languageId"
                                            required>
                                        <option value="">Sélectionnez...</option>
                                        <option value="1" {{ old('languageId') == '1' ? 'selected' : '' }}>Anglais</option>
                                        <option value="2" {{ old('languageId') == '2' ? 'selected' : '' }}>Italien</option>
                                        <option value="3" {{ old('languageId') == '3' ? 'selected' : '' }}>Japonais</option>
                                        <option value="4" {{ old('languageId') == '4' ? 'selected' : '' }}>Mandarin</option>
                                        <option value="5" {{ old('languageId') == '5' ? 'selected' : '' }}>Français</option>
                                        <option value="6" {{ old('languageId') == '6' ? 'selected' : '' }}>Allemand</option>
                                    </select>
                                    @error('languageId')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="rentalDuration" class="form-label">Durée de location (jours) <span class="text-danger">*</span></label>
                                    <input type="number"
                                           class="form-control @error('rentalDuration') is-invalid @enderror"
                                           id="rentalDuration"
                                           name="rentalDuration"
                                           value="{{ old('rentalDuration', 3) }}"
                                           min="1"
                                           required>
                                    @error('rentalDuration')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="rentalRate" class="form-label">Tarif location <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <input type="number"
                                               class="form-control @error('rentalRate') is-invalid @enderror"
                                               id="rentalRate"
                                               name="rentalRate"
                                               value="{{ old('rentalRate', '4.99') }}"
                                               step="0.01"
                                               min="0"
                                               required>
                                        <span class="input-group-text">€</span>
                                        @error('rentalRate')
                                            <div class="invalid-feedback">{{ $message }}</div>
                                        @enderror
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="mb-3">
                                    <label for="replacementCost" class="form-label">Coût de remplacement <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <input type="number"
                                               class="form-control @error('replacementCost') is-invalid @enderror"
                                               id="replacementCost"
                                               name="replacementCost"
                                               value="{{ old('replacementCost', '19.99') }}"
                                               step="0.01"
                                               min="0"
                                               required>
                                        <span class="input-group-text">€</span>
                                        @error('replacementCost')
                                            <div class="invalid-feedback">{{ $message }}</div>
                                        @enderror
                                    </div>
                                </div>
                            </div>
                        </div>

                        <hr class="my-4">

                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-check-circle"></i> Créer le film
                            </button>
                            <a href="{{ route('films.index') }}" class="btn btn-outline-secondary">
                                <i class="bi bi-x-circle"></i> Annuler
                            </a>
                        </div>
                    </form>
                </div>
            </div>

            <div class="mt-3">
                <div class="alert alert-info">
                    <i class="bi bi-info-circle"></i>
                    <small>Les champs marqués d'un <span class="text-danger">*</span> sont obligatoires.</small>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
