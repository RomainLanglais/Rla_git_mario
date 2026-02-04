<?php

namespace App\Http\Controllers;

use App\Services\ToadFilmService;
use Illuminate\Http\Request;

class FilmController extends Controller
{
    private ToadFilmService $filmService;

    public function __construct(ToadFilmService $filmService)
    {
        $this->middleware('auth');
        $this->filmService = $filmService;
    }

    public function index()
    {
        $films = $this->filmService->getAllFilms();

        return view('films.index', [
            'films' => $films ?? []
        ]);
    }

    public function show($id)
    {
        $film = $this->filmService->getFilmById($id);

        if (!$film) {
            abort(404, 'Film non trouvé');
        }

        return view('films.show', [
            'film' => $film
        ]);
    }

    public function create()
    {
        return view('films.create');
    }

    public function store(Request $request)
    {
        $validatedData = $request->validate([
            'title' => 'required|string|max:255',
            'description' => 'nullable|string',
            'releaseYear' => 'required|integer|min:1888|max:' . (date('Y') + 5),
            'languageId' => 'required|integer',
            'rentalDuration' => 'required|integer|min:1',
            'rentalRate' => 'required|numeric|min:0',
            'length' => 'required|integer|min:1',
            'replacementCost' => 'required|numeric|min:0',
            'rating' => 'required|string'
        ]);

        $result = $this->filmService->createFilm($validatedData);

        if ($result) {
            return redirect()
                ->route('films.index')
                ->with('success', 'Film créé avec succès !');
        }

        return back()
            ->withInput()
            ->with('error', 'Erreur lors de la création du film. Veuillez réessayer.');
    }
}