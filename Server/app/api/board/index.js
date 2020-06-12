const express = require('express')
const router = express.Router()
const board = require('./board')

module.exports = router

router.post('/write', board.write)
router.get('/load/:type', board.load)
router.get('/good/:id', board.good)
