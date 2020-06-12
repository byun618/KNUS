const express = require('express')
const router = express.Router()
const chat = require('./chat')

module.exports = router

router.post('/send', chat.send)
router.post('/load', chat.load)
router.post('/delete', chat.delete)
